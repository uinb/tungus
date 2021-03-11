package tech.uinb.tungus.service;

import java.util.List;
import tech.uinb.tungus.entity.ExtrinsicEvent;

public interface ExtrinsicEventService {

  void save(long extId, long eventId);

  List<Long> getEventIdByExtId(long extId);
}
