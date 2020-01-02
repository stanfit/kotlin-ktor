import kotlinx.serialization.Serializable

@Serializable
data class User(val id: Int, val name: String)

@Deprecated(message = "for debug")
val users = listOf(
    User(id = 1, name = "Sato"),
    User(id = 2, name = "Tanaka"),
    User(id = 3, name = "Suzuki")
)