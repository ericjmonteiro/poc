package br.com.wareline.domain.vo;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {

  @NotBlank
  @Schema(title = "Username", required = true)
  private String username;

  @NotBlank
  @Schema(title = "Password", required = true)
  private String password;

  @Schema(title = "User role, either ADMIN or USER. USER is default")
  private String role;

  @Schema(title = "User age", required = true)
  private Integer age;
}
