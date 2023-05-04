package org.sfy.ttrip.domain.usecase.chat

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.sfy.ttrip.domain.repository.chat.ChatRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateChatRoomUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(articleId: Int, opponentUserUuid: String) =
        withContext(Dispatchers.IO) {
            chatRepository.createChatRoom(articleId, opponentUserUuid)
        }
}