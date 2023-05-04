package org.sfy.ttrip.presentation.board

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.sfy.ttrip.MainActivity
import org.sfy.ttrip.R
import org.sfy.ttrip.databinding.FragmentBoardDetailBinding
import org.sfy.ttrip.presentation.base.BaseFragment

class BoardDetailFragment :
    BaseFragment<FragmentBoardDetailBinding>(R.layout.fragment_board_detail),
    BoardDialogListener,
    CommentDialogListener {

    private val args by navArgs<BoardDetailFragmentArgs>()
    private val viewModel by activityViewModels<BoardViewModel>()
    private val boardCommentListAdapter by lazy {
        BoardCommentListAdapter(this::selectComment, requireContext())
    }

    override fun initView() {
        (activity as MainActivity).hideBottomNavigation(true)
        getData()
        initListener()
        initRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).hideBottomNavigation(false)
    }

    override fun deleteBoard(boardId: Int) {
        viewModel.deleteBoard(boardId)
        popBackStack()
    }

    override fun finishBoard(boardId: Int) {
        viewModel.finishBoard(boardId)
        popBackStack()
    }

    override fun addComment(boardId: Int, content: String?) {
        if (content == "") showToast("내용을 입력하세요!")
        else viewModel.postComment(boardId, content)
        //viewModel.
    }

    private fun initListener() {
        binding.apply {
            tvFinishBoard.setOnClickListener {
                BoardDialog(
                    requireActivity(),
                    this@BoardDetailFragment,
                    boardDetail!!.articleId,
                    false
                ).show()
            }

            tvPostBoardComment.setOnClickListener {
                CommentDialog(
                    requireActivity(),
                    this@BoardDetailFragment,
                    boardDetail!!.articleId,
                ).show()
            }

            ivDeleteOption.setOnClickListener {
                BoardDialog(
                    requireActivity(),
                    this@BoardDetailFragment,
                    boardDetail!!.articleId,
                    true
                ).show()
            }

            ivBackToBoard.setOnClickListener {
                popBackStack()
                (activity as MainActivity).hideBottomNavigation(false)
            }
        }
    }

    private fun getData() {
        viewModel.boardData.observe(this@BoardDetailFragment) {
            binding.apply {
                boardDetail = it
                //ivBoardDetailUserProfile.setProfileImgString(it!!.imgPath)

                // 본인 게시물
                if (it!!.isMine) {
                    tvPostBoardComment.visibility = View.GONE
                    tvFinishBoard.visibility = View.VISIBLE

                    tvUserSimilarity.visibility = View.GONE
                    ivArrowBoardDetailUserDetail.visibility = View.GONE
                    ivDeleteOption.visibility = View.VISIBLE

                    if (it.status.toString() == "T") {
                        changeVisibility(tvFinishBoard, true)
                    } else {
                        changeVisibility(tvFinishBoard, false)
                    }
                } else {
                    // 타인 게시물
                    tvPostBoardComment.visibility = View.VISIBLE
                    tvFinishBoard.visibility = View.GONE

                    tvUserSimilarity.visibility = View.VISIBLE
                    ivArrowBoardDetailUserDetail.visibility = View.VISIBLE
                    ivDeleteOption.visibility = View.GONE

                    // 모집 진행중인 경우
                    if (it.status.toString() == "T") {
                        // 이미 신청한 경우
                        if (it.isApplied) {
                            changeVisibility(tvPostBoardComment, false)
                            tvPostBoardComment.text = "신청 완료"
                        } else {
                            changeVisibility(tvPostBoardComment, true)
                            tvPostBoardComment.text = "신청 하기"
                        }
                    } else {
                        changeVisibility(tvPostBoardComment, false)
                        tvPostBoardComment.text = "모집 완료"
                    }
                }

                tvBoardDetailContent.text = it.content
                tvDetailDuring.text = "${it.startDate} ~ ${it.endDate}"
                tvDetailNationCity.text = "${it.nation} - ${it.city}"

                val inputDateTime = it.createdDate
                val date = inputDateTime.substringBefore("T")
                val time = inputDateTime.substringAfter("T").substringBeforeLast(":")
                tvBoardDetailUserDate.text = "${date.replace("-", ".")} $time"

                tvUserSimilarity.apply {
                    if (it.similarity <= 50) {
                        setTextColor(ContextCompat.getColor(requireContext(), R.color.lochmara2))
                        setBackgroundResource(R.drawable.bg_rect_lochmara2_alice_blue2_radius10_stroke1)
                    } else if (it.similarity <= 80) {
                        setTextColor(ContextCompat.getColor(requireContext(), R.color.limerick))
                        setBackgroundResource(R.drawable.bg_rect_limerick_twilight_blue_radius10_stroke1)
                    } else {
                        setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.medium_orchid
                            )
                        )
                        setBackgroundResource(R.drawable.bg_rect_wisteria_white_lilac_radius10_stroke1)
                    }
                    text = "${it.similarity}%"
                }
            }
        }
        viewModel.getBoardDetail(args.boardId)
        viewModel.getBoardComment(args.boardId)

        binding.apply {
            if (args.dDay <= 3) {
                clBoardDetailTitle.setBackgroundResource(R.drawable.bg_rect_old_rose_top_radius20)
            } else if (args.dDay <= 10) {
                clBoardDetailTitle.setBackgroundResource(R.drawable.bg_rect_ming_top_radius20)
            } else {
                clBoardDetailTitle.setBackgroundResource(R.drawable.bg_rect_royal_blue_top_radius20)
            }
        }
    }

    private fun changeVisibility(textView: TextView, boolean: Boolean) {
        when (boolean) {
            true -> {
                textView.apply {
                    isEnabled = true
                    setBackgroundResource(R.drawable.bg_rect_pear_radius10)
                }
            }
            false -> {
                textView.apply {
                    isEnabled = false
                    setBackgroundResource(R.drawable.bg_rect_gainsboro_radius10)
                }
            }
        }
    }

    private fun initRecyclerView() {
        viewModel.getBoardComment(args.boardId)
        binding.rvBoardDetailComment.apply {
            adapter = boardCommentListAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        viewModel.boardCommentListData.observe(this@BoardDetailFragment) { response ->
            response?.let { boardCommentListAdapter.setBoardComment(it) }
        }
    }

    private fun selectComment(nickName: String) {

    }
}