package tech.uinb.tungus.entity.vo;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import tech.uinb.tungus.service.BlockService;

@Data
@Accessors(chain = true)
public class CallableVO<T> {

  private String id;
  private String events;
  private String data;
  private long timestamp;


  public static CallableVO valueOf(String id,String events,String data,long timestamp){
    return new CallableVO()
        .setId(id)
        .setEvents(events)
        .setTimestamp(timestamp)
        .setData(data);
  }

}
