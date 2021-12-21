package de.johanneswirth.apps.fantasyrealms.services

import de.johanneswirth.apps.common.{CommonError, IStatus}

import javax.validation.constraints.{Min, NotNull}
import javax.ws.rs.core.{Context, SecurityContext}
import javax.ws.rs.{Path, PathParam}


@Path("game/{gameID}")
class GameService {
  def getGame(@PathParam("gameID") @NotNull @Min(0) gameID: Long, @Context context: SecurityContext): IStatus = {
    CommonError.NO_ACCESS
  }
}
