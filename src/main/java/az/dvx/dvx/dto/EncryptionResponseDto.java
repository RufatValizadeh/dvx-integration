package az.dvx.dvx.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EncryptionResponseDto {
    private String encryptedPayload;
    private String encryptedKey;
}
