package domain.entity.users

data class User(
        val id: Int = 0,
        val name: String,
        val surname: String,
        val login: String,
        val password: String,
        val phoneNumber: String? = null,
        val email: String,
        val userRole: UserRole,
)