package lk.ijse.hostal.repository.custom.imple;

import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.entity.Custom;
import lk.ijse.hostal.entity.LoginDetails;
import lk.ijse.hostal.entity.embedded.Name;
import lk.ijse.hostal.repository.custom.CustomRepository;
import lk.ijse.hostal.repository.custom.LoginDetailsRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomRepositoryImple implements CustomRepository {

    @Override
    public List<Custom> getNotFullPaidList(Session session) {
        Query query = session.createQuery("SELECT rs.res_id,s.name, rs.remainingAmount, m.room_type_id, m.type FROM Reservation AS rs INNER JOIN Student AS s ON s.id=rs.studentId INNER JOIN Room AS m ON m.id=rs.room");
        query.setCacheable(true);
        final List<Object[]> list = query.getResultList();
        List<Custom> customList=new ArrayList<>();
        for (Object[] o : list) {
            customList.add(new Custom(
                    (String)o[0],
                    (Name) o[1],
                    (Double) o[2],
                    (String)o[3],
                    (RoomDTO.RoomType)o[4]
            ));
        }
        return customList;
    }
}
