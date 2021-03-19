package tech.uinb.tungus.service;

import java.util.List;

public interface ExtrinsicEventService {

  void save(long extId, long eventId);

  List<Long> getEventIdByExtId(long extId);
}
