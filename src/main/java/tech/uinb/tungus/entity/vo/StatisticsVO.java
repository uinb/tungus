package tech.uinb.tungus.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StatisticsVO {

  private String peldgeQuantity;

  private String StashQuantity;


}
