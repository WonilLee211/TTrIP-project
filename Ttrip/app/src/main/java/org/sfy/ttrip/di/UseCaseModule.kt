package org.sfy.ttrip.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sfy.ttrip.domain.repository.auth.AuthRepository
import org.sfy.ttrip.domain.repository.board.BoardRepository
import org.sfy.ttrip.domain.repository.chat.ChatRepository
import org.sfy.ttrip.domain.repository.landmark.LandmarkRepository
import org.sfy.ttrip.domain.repository.live.LiveRepository
import org.sfy.ttrip.domain.repository.mypage.MyPageRepository
import org.sfy.ttrip.domain.repository.user.UserRepository
import org.sfy.ttrip.domain.usecase.auth.AccessTokenUseCase
import org.sfy.ttrip.domain.usecase.auth.LoginUseCase
import org.sfy.ttrip.domain.usecase.auth.SignUpUseCase
import org.sfy.ttrip.domain.usecase.board.DeleteBoardUseCase
import org.sfy.ttrip.domain.usecase.board.FinishBoardUseCase
import org.sfy.ttrip.domain.usecase.board.GetBoardBriefUseCase
import org.sfy.ttrip.domain.usecase.board.GetBoardCommentUseCase
import org.sfy.ttrip.domain.usecase.board.GetBoardDetailUseCase
import org.sfy.ttrip.domain.usecase.board.PostBoardUseCase
import org.sfy.ttrip.domain.usecase.board.PostCommentUseCase
import org.sfy.ttrip.domain.usecase.chat.ChatMatchUseCase
import org.sfy.ttrip.domain.usecase.chat.CreateChatRoomUseCase
import org.sfy.ttrip.domain.usecase.chat.ExitChatRoomUseCase
import org.sfy.ttrip.domain.usecase.chat.GetChatDetailUseCase
import org.sfy.ttrip.domain.usecase.chat.GetChatRoomsUseCase
import org.sfy.ttrip.domain.usecase.landmark.CreateDoodleUseCase
import org.sfy.ttrip.domain.usecase.landmark.GetDoodlesUseCase
import org.sfy.ttrip.domain.usecase.landmark.GetBadgesUseCase
import org.sfy.ttrip.domain.usecase.landmark.GetLandmarksUseCase
import org.sfy.ttrip.domain.usecase.landmark.IssueBadgeUseCase
import org.sfy.ttrip.domain.usecase.live.CreateSessionUseCase
import org.sfy.ttrip.domain.usecase.live.GetCallTokenUseCase
import org.sfy.ttrip.domain.usecase.live.GetLiveUsersUseCase
import org.sfy.ttrip.domain.usecase.mypage.*
import org.sfy.ttrip.domain.usecase.user.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Singleton
    @Provides
    fun provideSignUpUseCase(authRepository: AuthRepository): SignUpUseCase =
        SignUpUseCase(authRepository)

    @Singleton
    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase =
        LoginUseCase(authRepository)

    @Singleton
    @Provides
    fun provideGetLiveUsersUseCase(liveRepository: LiveRepository): GetLiveUsersUseCase =
        GetLiveUsersUseCase(liveRepository)

    @Singleton
    @Provides
    fun provideUpdateUserInfoUSeCase(myPageRepository: MyPageRepository): UpdateUserInfoUseCase =
        UpdateUserInfoUseCase(myPageRepository)

    @Singleton
    @Provides
    fun provideCheckDuplicationUseCase(userRepository: UserRepository): CheckDuplicationUseCase =
        CheckDuplicationUseCase(userRepository)

    @Singleton
    @Provides
    fun providePostUserInfoUseCase(userRepository: UserRepository): PostUserInfoUseCase =
        PostUserInfoUseCase(userRepository)

    @Singleton
    @Provides
    fun provideGetCallTokenUseCase(liveRepository: LiveRepository): GetCallTokenUseCase =
        GetCallTokenUseCase(liveRepository)

    @Singleton
    @Provides
    fun provideGetBoardBriefUseCase(boardRepository: BoardRepository): GetBoardBriefUseCase =
        GetBoardBriefUseCase(boardRepository)

    @Singleton
    @Provides
    fun provideGetBoardDetailUseCase(boardRepository: BoardRepository): GetBoardDetailUseCase =
        GetBoardDetailUseCase(boardRepository)

    @Singleton
    @Provides
    fun provideDeleteBoardUseCase(boardRepository: BoardRepository): DeleteBoardUseCase =
        DeleteBoardUseCase(boardRepository)

    @Singleton
    @Provides
    fun provideFinishBoardUseCase(boardRepository: BoardRepository): FinishBoardUseCase =
        FinishBoardUseCase(boardRepository)

    @Singleton
    @Provides
    fun provideGetBoardCommentUseCase(boardRepository: BoardRepository): GetBoardCommentUseCase =
        GetBoardCommentUseCase(boardRepository)

    @Singleton
    @Provides
    fun provideUpdateBackgroundImgUseCase(myPageRepository: MyPageRepository): UpdateBackgroundImgUseCase =
        UpdateBackgroundImgUseCase(myPageRepository)

    @Singleton
    @Provides
    fun provideUpdateProfileImgUseCase(myPageRepository: MyPageRepository): UpdateProfileImgUseCase =
        UpdateProfileImgUseCase(myPageRepository)

    @Singleton
    @Provides
    fun providePostCommentUseCase(boardRepository: BoardRepository): PostCommentUseCase =
        PostCommentUseCase(boardRepository)

    @Singleton
    @Provides
    fun provideGetChatRoomsUseCase(chatRepository: ChatRepository): GetChatRoomsUseCase =
        GetChatRoomsUseCase(chatRepository)

    @Singleton
    @Provides
    fun provideExitChatRoomUseCase(chatRepository: ChatRepository): ExitChatRoomUseCase =
        ExitChatRoomUseCase(chatRepository)

    @Singleton
    @Provides
    fun provideGetChatDetailUseCase(chatRepository: ChatRepository): GetChatDetailUseCase =
        GetChatDetailUseCase(chatRepository)

    @Singleton
    @Provides
    fun provideCreateChatRoomUseCase(chatRepository: ChatRepository): CreateChatRoomUseCase =
        CreateChatRoomUseCase(chatRepository)

    @Singleton
    @Provides
    fun provideGetUserProfileUseCase(myPageRepository: MyPageRepository): GetUserProfileUseCase =
        GetUserProfileUseCase(myPageRepository)

    @Singleton
    @Provides
    fun providePostBoardUseCase(boardRepository: BoardRepository): PostBoardUseCase =
        PostBoardUseCase(boardRepository)

    @Singleton
    @Provides
    fun provideGetUserProfileDialogUseCase(userRepository: UserRepository): GetUserProfileDialogUseCase =
        GetUserProfileDialogUseCase(userRepository)

    @Singleton
    @Provides
    fun providePostUserFcmTokenUseCase(userRepository: UserRepository): PostUserFcmTokenUseCase =
        PostUserFcmTokenUseCase(userRepository)

    @Singleton
    @Provides
    fun provideChatMatchUseCase(chatRepository: ChatRepository): ChatMatchUseCase =
        ChatMatchUseCase(chatRepository)

    @Singleton
    @Provides
    fun provideCreateSessionUseCase(liveRepository: LiveRepository): CreateSessionUseCase =
        CreateSessionUseCase(liveRepository)

    @Singleton
    @Provides
    fun provideGetMyPostsUseCase(myPageRepository: MyPageRepository): GetMyPostsUseCase =
        GetMyPostsUseCase(myPageRepository)

    @Singleton
    @Provides
    fun provideLogoutUseCase(myPageRepository: MyPageRepository): LogoutUseCase =
        LogoutUseCase(myPageRepository)

    @Singleton
    @Provides
    fun providePostUserInfoTestUseCase(userRepository: UserRepository): PostUserInfoTestUseCase =
        PostUserInfoTestUseCase(userRepository)

    @Singleton
    @Provides
    fun provideUpdatePreferencesUseCase(myPageRepository: MyPageRepository): UpdatePreferencesUseCase =
        UpdatePreferencesUseCase(myPageRepository)

    @Singleton
    @Provides
    fun provideGetLandmarksUseCase(landmarkRepository: LandmarkRepository): GetLandmarksUseCase =
        GetLandmarksUseCase(landmarkRepository)

    @Singleton
    @Provides
    fun provideCreateDoodleUseCase(landmarkRepository: LandmarkRepository): CreateDoodleUseCase =
        CreateDoodleUseCase(landmarkRepository)

    @Singleton
    @Provides
    fun provideGetDoodlesUseCase(landmarkRepository: LandmarkRepository): GetDoodlesUseCase =
        GetDoodlesUseCase(landmarkRepository)

    @Singleton
    @Provides
    fun provideGetBadgesUseCase(landmarkRepository: LandmarkRepository): GetBadgesUseCase =
        GetBadgesUseCase(landmarkRepository)

    @Singleton
    @Provides
    fun provideIssueBadgeUseCase(landmarkRepository: LandmarkRepository): IssueBadgeUseCase =
        IssueBadgeUseCase(landmarkRepository)

    @Singleton
    @Provides
    fun provideRequestAccessToken(authRepository: AuthRepository): AccessTokenUseCase =
        AccessTokenUseCase(authRepository)

    @Singleton
    @Provides
    fun providePostEvaluateUserUseCase(userRepository: UserRepository): PostEvaluateUserUseCase =
        PostEvaluateUserUseCase(userRepository)

    @Singleton
    @Provides
    fun providePostReportUserUseCase(userRepository: UserRepository): PostReportUserUseCase =
        PostReportUserUseCase(userRepository)

    @Singleton
    @Provides
    fun provideCertificateProfileUseCase(myPageRepository: MyPageRepository): CertificateProfileUseCase =
        CertificateProfileUseCase(myPageRepository)
}