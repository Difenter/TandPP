package domain.entity.workout

data class Exercise(
    val id: Long = 0,
    val name: String,
    val sets: Byte,
    val repetitionsFrom: Short,
    val repetitionsTo: Short,
    val weightFrom: Short? = null,
    val weightTo: Short? = null,
    val time: Time? = null,
    val notes: String? = null,
    var completedSets: List<CompletedSet>? = null,
    val workoutId: Long,
)