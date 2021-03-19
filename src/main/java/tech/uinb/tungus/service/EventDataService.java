package tech.uinb.tungus.service;

import java.util.List;
import tech.uinb.tungus.entity.EventData;

public interface EventDataService {

  long save(byte[] data);

  EventData getEventDataById(long id);

  List getEvenByExtId(long id);

  String getEventsDataByExtId(long id);
}