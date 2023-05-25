package br.com.example.retrotrit2.model;

import lombok.Builder;
import lombok.Data;

/*
 *Passo 4 - Criar classe de modelo de acordo com o recurso
 * */
@Data
@Builder
public class Post {
    private Integer userId, id;
    private String title, body;
}
