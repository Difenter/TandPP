package domain.entity.workout

data class CompletedSet(
        val id: Long = 0,
        val setNumber: Byte,
        val repetitions: Short,
        val weight: Short? = null,
        val time: Time? = null,
        val notes: String? = null,
        val exerciseId: Long,
)