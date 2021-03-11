package tech.uinb.tungus.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("page requst params")
@Data
@Accessors(chain = true)
public class PagePO {

  @ApiModelProperty("rows")
  private Integer size;
  @ApiModelProperty("page index")
  private Integer page;

}
