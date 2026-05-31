package oscar.c.pozas.playground.context.user.primaryadapter.rest.create

data class CreateUserInputModel(
    val username: String,
    val email: String,
)

data class CreateUserResponse(
    val id: String
)