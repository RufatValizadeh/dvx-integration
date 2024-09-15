package az.dvx.dvx.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EncryptionRequestDto {
    private String voen;
    private String aesKey;
}
