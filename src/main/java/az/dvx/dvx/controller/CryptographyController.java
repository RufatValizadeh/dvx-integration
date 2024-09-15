package az.dvx.dvx.controller;

import az.dvx.dvx.dto.DecryptionRequestDto;
import az.dvx.dvx.dto.DecryptionResponseDto;
import az.dvx.dvx.dto.EncryptionRequestDto;
import az.dvx.dvx.dto.EncryptionResponseDto;
import az.dvx.dvx.util.CryptographyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cryptography")
public class CryptographyController {

    @PostMapping("/encrypt")
    public ResponseEntity<EncryptionResponseDto> encrypt(@RequestBody EncryptionRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(CryptographyUtil.encrypt(dto.getVoen(),dto.getAesKey()));
    }
    @PostMapping("/decrypt")
    public ResponseEntity<String> decrypt(@RequestBody DecryptionRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(CryptographyUtil.decrypt(dto.getEncryptedText(),dto.getAesKey()));
    }
}
