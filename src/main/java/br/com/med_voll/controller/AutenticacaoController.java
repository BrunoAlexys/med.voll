package br.com.med_voll.controller;

import br.com.med_voll.infra.security.DadostokenJWT;
import br.com.med_voll.infra.security.TokenService;
import br.com.med_voll.model.user.DadosAutenticacao;
import br.com.med_voll.model.user.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService service;
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        var authenticationon = manager.authenticate(token);
        var tokenJWT = service.gerarToken((Usuario) authenticationon.getPrincipal());

        return ResponseEntity.ok(new DadostokenJWT(tokenJWT));
    }
}
