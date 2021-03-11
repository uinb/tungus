package tech.uinb.tungus.service;

import java.util.List;

public interface AccountStashService {

  void save(long accountId, long extId);

  List<Long> getStashIdsByAccount(long accountId);
}
