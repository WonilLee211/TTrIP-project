package org.sfy.ttrip.presentation.landmark

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.ar.core.Anchor
import com.google.ar.core.Pose
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import dagger.hilt.android.AndroidEntryPoint
import org.sfy.ttrip.MainActivity
import org.sfy.ttrip.R
import org.sfy.ttrip.databinding.FragmentDoodleBinding
import org.sfy.ttrip.presentation.base.BaseFragment
import java.io.File

@AndroidEntryPoint
class DoodleFragment : BaseFragment<FragmentDoodleBinding>(R.layout.fragment_doodle),
    DrawDoodleDialogListener {

    private var arFragment: ArFragment? = null
    private var isObjectPlaced = false
    private var bitmap: Bitmap? = null
    private val landmarkViewModel by viewModels<LandmarkViewModel>()
    private val args by navArgs<DoodleFragmentArgs>()

    override fun initView() {
        (activity as MainActivity).hideBottomNavigation(true)
        initListener()
        setARFunction()
        setDoodles()
    }

    private fun initListener() {
        binding.ivAddDoodle.setOnClickListener {
            val dialog = DrawDoodleDialog(requireContext(), this)
            dialog.show()
        }
        binding.ivSaveDoodle.setOnClickListener {
            bitmap?.let {
                sendImageToServer(it)
            }
            showToast("낙서가 저장되었습니다!")
            binding.ivSaveDoodle.visibility = View.GONE
        }
    }

    private fun setDoodles() {
        landmarkViewModel.doodles.observe(viewLifecycleOwner) {
            if (it != null) {
                for (item in it) {
                    ViewRenderable.builder()
                        .setView(context, R.layout.item_doodle)
                        .build()
                        .thenAccept { renderable ->
                            val imageView =
                                renderable.view.findViewById<ImageView>(R.id.iv_doodle_view) // 프리뷰 이미지에 사용할 ImageView
                            Glide.with(requireContext())
                                .asBitmap()
                                .load("http://k8d104.p.ssafy.io:8081/images${item.doodleImgPath}")
                                .into(object : CustomTarget<Bitmap>() {
                                    override fun onResourceReady(
                                        bitmap: Bitmap,
                                        transition: Transition<in Bitmap>?
                                    ) {
                                        imageView.setImageBitmap(bitmap)
                                    }

                                    override fun onLoadCleared(placeholder: Drawable?) {
                                    }
                                })
                            placeExistingDoodles(
                                renderable,
                                Vector3(
                                    item.positionX.toFloat(),
                                    item.positionY.toFloat(),
                                    item.positionZ.toFloat()
                                )
                            )
                        }
                }
            }
        }
        landmarkViewModel.getDoodles(args.landmarkId)
    }

    private fun setARFunction() {
        arFragment = childFragmentManager.findFragmentById(R.id.arContentFragment) as ArFragment?
//        arFragment?.setOnTapArPlaneListener { hitResult, _, _ ->
//            // 추가적인 배치가 불가능한 상태일 경우
//            if (isObjectPlaced) {
//                return@setOnTapArPlaneListener
//            }
//            ViewRenderable.builder()
//                .setView(requireContext(), R.layout.item_doodle)
//                .build()
//                .thenAccept { renderable ->
//                    val imageView = renderable.view.findViewById<ImageView>(R.id.iv_doodle_view)
//                    imageView.setImageBitmap(bitmap)
//                    placeImageOnArSurface(renderable, hitResult.createAnchor())
//                    setDoodles()
//                }
//            // 사물 배치 상태 변수 변경
//            isObjectPlaced = true
//        }


        // 실패
        ViewRenderable.builder()
            .setView(context, R.layout.item_doodle)
            .build()
            .thenAccept { renderable ->
                val imageView = renderable.view.findViewById<ImageView>(R.id.iv_doodle_view)
                imageView.setImageBitmap(bitmap)
                arFragment?.setOnTapArPlaneListener { hitResult, _, _ ->
                    // 추가적인 배치가 불가능한 상태일 경우
                    if (isObjectPlaced) {
                        return@setOnTapArPlaneListener
                    }
                    placeImageOnArSurface(renderable, hitResult.createAnchor())
                    // setDoodles()
                    // 사물 배치 상태 변수 변경
                    isObjectPlaced = true
                }
            }

    }

    private fun placeExistingDoodles(viewRenderable: ViewRenderable, position: Vector3) {
        arFragment?.let { fragment ->
            fragment.arSceneView!!.let { sceneView ->
                Log.d("엥", "Pose: ${Pose.makeTranslation(position.x, position.y, position.z)}")
                val anchor = sceneView.session!!.createAnchor(
                    Pose.makeTranslation(position.x, position.y, position.z)
                )
                anchor.let { anchor ->
                    val anchorNode = AnchorNode(anchor)
                    anchorNode.setParent(sceneView.scene)

                    val node = TransformableNode(fragment.transformationSystem)
                    node.setParent(anchorNode)
                    node.renderable = viewRenderable
                    node.select()
                }
            }
        }
    }

    private fun placeImageOnArSurface(viewRenderable: ViewRenderable, anchor: Anchor) {
        val anchorNode = AnchorNode(anchor)
        viewRenderable.isShadowCaster = false
        val transformableNode = TransformableNode(arFragment!!.transformationSystem)
        transformableNode.apply {
            setParent(anchorNode)
            scaleController.maxScale = 0.5f
            scaleController.minScale = 0.05f
            renderable = viewRenderable
        }
        arFragment!!.arSceneView.scene.addChild(anchorNode)
        transformableNode.select()

        // 위치 정보 및 사진을 서버에 전송
        val position = transformableNode.worldPosition
        landmarkViewModel.setPositionX(position.x.toDouble())
        landmarkViewModel.setPositionY(position.y.toDouble())
        landmarkViewModel.setPositionZ(position.z.toDouble())
    }

    private fun sendImageToServer(bitmap: Bitmap) {
        val file = File(requireContext().cacheDir, "temp_image.jpg")
        file.outputStream().use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
        }
        landmarkViewModel.setDoodleImg(file)
        landmarkViewModel.createDoodle(args.landmarkId, 36.108, 128.4185)
    }

    override fun onConfirmButtonClicked(bitmap: Bitmap) {
        this.bitmap = bitmap
        binding.ivSaveDoodle.visibility = View.VISIBLE
        binding.ivAddDoodle.visibility = View.GONE
        showToast("낙서가 생성되었습니다.\n낙서를 배치해보세요!")
    }
}