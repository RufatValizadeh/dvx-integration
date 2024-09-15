package az.dvx.dvx.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DecryptionRequestDto {
    private String encryptedText;
    private String aesKey;
}
