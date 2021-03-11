package tech.uinb.tungus.service;

import tech.uinb.tungus.entity.EventData;

public interface EventDataService {

  long save(byte[] data);

  EventData getEventDataById(long id);
}
