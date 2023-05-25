package br.com.example.retrotrit2.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.example.retrotrit2.R;
import br.com.example.retrotrit2.bootstrap.APIClient;
import br.com.example.retrotrit2.model.Post;
import br.com.example.retrotrit2.resource.PostResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PostActivity extends AppCompatActivity {

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    //Métod de exemplo para listar os dados de um serviço na internet
    //Utilizando o retrofit
    public void listarPosts(View view) {
        //Passo 6 - Criar função para trabalhar com o retrofit

        Retrofit retrofit = APIClient.getClient();

        //Fazer a Inverção de Controle e injeção de dependência da interface
        //(contrato) PostResource

        PostResource postResource = retrofit.create(PostResource.class);

        //Fazer o método/operação pretendido.

        Call<List<Post>> lista = postResource.get();

        // Utilizar a estrutura de dados FILA (FIFO) para trabalhar
        //com chamadas assíncronas.

        lista.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                // O método onResponse retorna os dados do recurso(resource) consumido.
                List<Post> posts = response.body();

                for (int i = 0; i < posts.size(); i++) {
                    Log.i("post", String.format("%d %s", i, posts.get(i).toString()));
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                //Método responsável pelos erros.
                Toast.makeText(getApplicationContext(), "Ocorreu um erro no serviço.", Toast.LENGTH_LONG).show();
            }
        });
    }
}