package services

import dao.UserDAO
import entity.UserEntity
import java.util.*

class UserService(private val userDao: UserDAO) : IUserService {
    override fun create(user: UserEntity): UserEntity? {
        return userDao.create(user)
    }

    override fun getById(id: UUID): UserEntity? {
        return userDao.getById(id)
    }

    override fun update(user: UserEntity): UserEntity? {
        return userDao.update(user)
    }

    override fun delete(id: UUID):Boolean {
        return userDao.delete(id)
    }

    override fun getAll(): List<UserEntity>? {
        return userDao.getAll()
    }
}