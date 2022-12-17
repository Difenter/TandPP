package domain.entity.users

data class RequestDB(
        val id: Int = 0,
        val trainerId: Int,
        val clientId: Int,
        val status: RequestStatus = RequestStatus.UNCHECKED,
)