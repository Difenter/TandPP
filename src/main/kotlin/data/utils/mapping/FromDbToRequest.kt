package data.utils.mapping

import domain.entity.users.Request
import domain.entity.users.RequestDB
import domain.entity.users.RequestStatus
import java.sql.ResultSet

fun ResultSet.toRequest() = RequestDB(
        id = this.getInt(1),
        trainerId = this.getInt(2),
        clientId = this.getInt(3),
        status = RequestStatus.valueOf(this.getString(4).toUpperCase())
)

fun Request.toRequestDB() = RequestDB(
        id = this.id,
        trainerId = this.trainer.id,
        clientId = this.client.id,
        status = this.status
)