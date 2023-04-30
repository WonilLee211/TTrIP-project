package org.sfy.ttrip.data.remote.repository

import okhttp3.MultipartBody
import org.sfy.ttrip.common.util.wrapToResource
import org.sfy.ttrip.data.remote.datasorce.user.UserRemoteDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRemoteDataSource {

    override suspend fun patchUserInfo(
        nickName: String,
        intro: String,
        gender: String,
        profileFile: MultipartBody.Part?,
        markerFile: MultipartBody.Part?,
        age: String,
        fcmToken: String
    ) {
        wrapToResource {
            userRemoteDataSource.patchUserInfo(
                nickName,
                intro,
                gender,
                profileFile,
                markerFile,
                age,
                fcmToken
            )
        }
    }
}