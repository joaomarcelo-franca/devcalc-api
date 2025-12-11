# DevCalc API

Projeto **DevCalc API** — Aplicação Java utilizada para testes de **CI/CD** e implantação em **Kubernetes**.

Build: **Maven**

(README final será escrito depois)

[![Hello CI/CD](https://github.com/joaomarcelo-franca/devcalc-api/actions/workflows/hello.yml/badge.svg)](https://github.com/joaomarcelo-franca/devcalc-api/actions/workflows/hello.yml)

---

# 📄 README — Atividade Kubernetes (Deployment, Service e Teste BusyBox)

## 📌 1. Visão Geral

Este repositório contém toda a implementação necessária para a atividade prática envolvendo Kubernetes:

- Deployment de uma aplicação Java (DevCalc API)
- Probes funcionais (Liveness/Readiness)
- Escalonamento imperativo via `kubectl`
- Deployment do Nginx
- Service ClusterIP acessível apenas dentro do cluster
- Teste interno usando BusyBox para validar o redirecionamento

---

# 📁 2. Estrutura do Projeto

/
├── deployment.yaml # Deployment da DevCalc API
├── nginx-deployment.yaml # Deployment do Nginx
├── nginx-service.yaml # Service ClusterIP
└── README.md

yaml
Copiar código

---

# 🚀 3. Deployment da Aplicação (DevCalc API)

A aplicação foi empacotada em uma imagem Docker hospedada em:

joaofranca2005/devcalc-api:latest

markdown
Copiar código

O arquivo **deployment.yaml** contém:

- 2 réplicas
- Probes funcionais acessando `/health`
- Porta container 7000
- Liveness e Readiness configurados corretamente

### ▶ Aplicando o Deployment

```sh 
kubectl apply -f deployment.yaml
kubectl get deployment my-deployment
kubectl get pods -l app=my-app
```
📈 4. Escalonamento Imperativo
O Deployment inicial possui 2 réplicas.
Escalonamento para 3 réplicas via comando imperativo:

```sh
Copiar código
kubectl scale deployment my-deployment --replicas=3
Verificar:
```
```sh
Copiar código
kubectl get deployment my-deployment
kubectl get pods -l app=my-app
```
🌐 5. Deployment do Nginx
Arquivo: nginx-deployment.yaml

Configuração:

Deployment com 1 réplica

Imagem nginx:latest

Exposição da porta 80

Aplicando:

```sh
Copiar código
kubectl apply -f nginx-deployment.yaml
kubectl get pods -l app=nginx-app
```
🔌 6. Service ClusterIP (Interno)
Arquivo: nginx-service.yaml

Configuração:

Tipo: ClusterIP

Porta exposta: 8081

Redirecionamento para a porta 80 do pod nginx

Disponível somente dentro do cluster

Aplicando:

```sh
Copiar código
kubectl apply -f nginx-service.yaml
kubectl get service nginx-service
```
🧪 7. Teste Interno com BusyBox
Criar o pod BusyBox:

```sh
Copiar código
kubectl run busybox-test --image=busybox:latest -it --restart=Never -- sh
```
Caso já exista:


```sh
Copiar código
kubectl exec -it busybox-test -- sh
```
🌐 8. Testando o Service (Redirecionamento Funcional)
Dentro do BusyBox:

```sh
Copiar código
wget -qO- http://nginx-service:8081
```
📌 Resultado esperado (HTML do Nginx):

php-template
Copiar código
<!DOCTYPE html>
<html>
<head>
<title>Welcome to nginx!</title>
...
Isso comprova:

O Service está funcionando

O redirecionamento 8081 → 80 está correto

O pod Nginx recebeu a requisição