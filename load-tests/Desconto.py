from locust import HttpUser, task, between
import random
from datetime import datetime, timedelta

class DescontoUser(HttpUser):

    wait_time = between(1, 3)

    def on_start(self):
        # LOGIN
        response = self.client.post(
            "/auth/login",
            json={
                "nomeUsuario": "admin",
                "senha": "123456"
            }
        )

        if response.status_code == 200:
            self.token = response.json()["token"]
        else:
            self.token = None
            print("Erro ao logar:", response.text)

    def gerar_desconto(self):
        now = datetime.now()
        futuro = now + timedelta(days=10)

        return f"""
        <Desconto>
            <codigo>DESC{random.randint(1000,9999)}</codigo>
            <descricao>Desconto teste</descricao>
            <percentualDesconto>10.5</percentualDesconto>
            <inicioEm>{now.isoformat()}</inicioEm>
            <fimEm>{futuro.isoformat()}</fimEm>
        </Desconto>
        """

    @task(2)
    def listar_descontos(self):
        if self.token:
            self.client.get(
                "/descontos",
                headers={"Authorization": f"Bearer {self.token}"}
            )

    @task(1)
    def criar_desconto(self):
        if self.token:
            payload = self.gerar_desconto()

            self.client.post(
                "/descontos",
                data=payload,
                headers={
                    "Content-Type": "application/xml",
                    "Authorization": f"Bearer {self.token}"
                }
            )