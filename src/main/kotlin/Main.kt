import dao.UserDAOH2
import dataSource.DataSourceFactory
import entity.UserEntity
import output.Console
import services.UserService

fun main() {

    val console = Console()

    // Creamos la instancia de la base de datos
    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)

    // Creamos la instancia de UserDAO
    val userDao = UserDAOH2(dataSource, console)

    // Creamos la instancia de UserService
    val userService = UserService(userDao)

    // Creamos un nuevo usuario
    val newUser = UserEntity(name = "John Doe", email = "johndoe@example.com")
    var createdUser = userService.create(newUser)

    console.showMessage("Created user: ${createdUser ?: "error"}")



    // Obtenemos un usuario por su ID
    val foundUser =
        if (createdUser != null )userService.getById(createdUser.id)
        else null
    console.showMessage("Found user: ${foundUser ?: "error"}")

    // Actualizamos el usuario
    val updatedUser = foundUser?.copy(name = "Jane Doe")
    val savedUser =
        if (updatedUser != null) userService.update(updatedUser)
        else null
    console.showMessage("Updated user: $${savedUser ?: "error"}")

    val otherUser = UserEntity(name = "Eduardo Fernandez", email = "eferoli@gmail.com")
    createdUser = userService.create( otherUser)
    console.showMessage("Created user: $${createdUser ?: "error"}")


    // Obtenemos todos los usuarios
    var allUsers = userService.getAll()
    console.show(allUsers)

    // Eliminamos el usuario
    if (savedUser != null) {
        if (userService.delete(savedUser.id)) console.showMessage("User deleted OK!")
        else console.showMessage("User deleted error!")
    }

    // Obtenemos todos los usuarios
    allUsers = userService.getAll()
    console.show(allUsers)

    // Eliminamos el usuario

    if (userService.delete(otherUser.id)) console.showMessage("User deleted OK!")
    else console.showMessage("User deleted error!")

    // Obtenemos todos los usuarios
    allUsers = userService.getAll()
    console.show(allUsers)

}