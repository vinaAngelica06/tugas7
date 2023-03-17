package Controller;
import Model.HasilPanen;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Path("hasilkebun")
public class HasilPanenResource {
    @GET
    public List<HasilPanen> getData(){
        return HasilPanen.listAll();
    }
    @POST
    @Transactional
    public HasilPanen addData(JsonObject body){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        HasilPanen hasilPanen = new HasilPanen();
        hasilPanen.total = body.getInt("total");
        hasilPanen.komoditas = body.getString("komoditas");
        hasilPanen.createdat = dtf.format(date).toString();
        hasilPanen.updatedat = dtf.format(date).toString();
        hasilPanen.persist();
        return hasilPanen;
    }
    @PUT
    @Path("{uuid}")
    @Transactional
    public JsonObject editHasilPanen(@PathParam("uuid")String uuid, JsonObject body){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        Integer total = body.getInt("total");
        String updatedAt = dtf.format(date).toString();
        HasilPanen.update("total = ?1,updatedat = ?2 where id = ?3",total,updatedAt,uuid);
        return body;
    }
    @DELETE
    @Path("{uuid}")
    @Transactional
    public Long deleteHasilPanen(@PathParam("uuid")String uuid){
        return HasilPanen.delete("id = ?1",uuid);
    }
}
