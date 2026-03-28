from locust import HttpUser, task, between
import random

class CategoriaUser(HttpUser):
    wait_time = between(1, 3)

    def on_start(self):
        self.headers = {
            "Content-Type": "application/xml",
            "Accept": "application/xml"
        }

    @task
    def criar_e_buscar_categoria(self):
        nome = f"Categoria{random.randint(1, 100000)}"

        xml_body = f"""
        <Categoria>
            <nome>{nome}</nome>
            <descricao>Teste de carga</descricao>
        </Categoria>
        """

        # 🔹 POST - criar categoria
        response = self.client.post(
            "/categorias",
            data=xml_body,
            headers=self.headers
        )

        if response.status_code == 201:
            try:
                # pega o ID do XML retornado
                text = response.text
                start = text.find("<id>") + 4
                end = text.find("</id>")
                categoria_id = text[start:end]

                # 🔹 GET - buscar categoria criada
                self.client.get(
                    f"/categorias/id/{categoria_id}",
                    headers=self.headers
                )

            except Exception as e:
                print("Erro ao processar resposta:", e)