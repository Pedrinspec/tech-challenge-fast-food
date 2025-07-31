#!/bin/bash

echo "Iniciando a implantação da aplicação FastFood no Kubernetes..."

# 1. Criação do Namespace
echo "Criando o Namespace 'desafio-fiap'..."
kubectl apply -f namespace.yaml
if [ $? -ne 0 ]; then
    echo "Erro ao criar o Namespace. Abortando."
    exit 1
fi
echo "Namespace 'desafio-fiap' criado com sucesso."
echo ""

# 2. Criação dos Segredos (Secrets)
echo "Criando os Secrets 'mysql-secret' e 'fastfood-app-secret'..."
kubectl apply -f mysql-secret.yaml
if [ $? -ne 0 ]; then
    echo "Erro ao criar o Secret 'mysql-secret'. Abortando."
    exit 1
fi
kubectl apply -f secret-api.yaml
if [ $? -ne 0 ]; then
    echo "Erro ao criar o Secret 'fastfood-app-secret'. Abortando."
    exit 1
fi
echo "Secrets criados com sucesso."
echo ""

# 3. Criação dos ConfigMaps
echo "Criando os ConfigMaps 'mysql-config' e 'fastfood-app-config'..."
kubectl apply -f mysql-configmap.yaml
if [ $? -ne 0 ]; then
    echo "Erro ao criar o ConfigMap 'mysql-config'. Abortando."
    exit 1
fi
kubectl apply -f configmap-api.yaml
if [ $? -ne 0 ]; then
    echo "Erro ao criar o ConfigMap 'fastfood-app-config'. Abortando."
    exit 1
fi
echo "ConfigMaps criados com sucesso."
echo ""

# 4. Implantação do Banco de Dados MySQL (StatefulSet e Service)
echo "Implantando o Banco de Dados MySQL (StatefulSet e Service)..."
kubectl apply -f mysql-service.yaml
if [ $? -ne 0 ]; then
    echo "Erro ao criar o Service 'mysql'. Abortando."
    exit 1
fi
kubectl apply -f mysql-statefulset.yaml
if [ $? -ne 0 ]; then
    echo "Erro ao criar o StatefulSet 'mysql'. Abortando."
    exit 1
fi
echo "MySQL implantado com sucesso."
echo ""

# Espera o MySQL ficar pronto (opcional, mas recomendado para evitar erros na API)
echo "Aguardando o MySQL estar pronto (pode levar alguns minutos)..."
kubectl wait --for=condition=ready pod -l app=mysql -n desafio-fiap --timeout=300s
if [ $? -ne 0 ]; then
    echo "O MySQL não ficou pronto a tempo. Verifique os logs."
    # Não aborta, mas informa que o MySQL pode estar com problemas
fi
echo "MySQL pronto ou timeout atingido. Prosseguindo com a implantação da API."
echo ""

# 5. Implantação da API (Deployment e Service)
echo "Implantando a aplicação FastFood API (Deployment e Service)..."
kubectl apply -f deployment-api.yaml
if [ $? -ne 0 ]; then
    echo "Erro ao criar o Deployment 'fastfood-app'. Abortando."
    exit 1
fi
kubectl apply -f service-api.yaml
if [ $? -ne 0 ]; then
    echo "Erro ao criar o Service 'fastfood-app'. Abortando."
    exit 1
fi
echo "FastFood API implantada com sucesso."
echo ""

# 6. Aplicação do HPA (Horizontal Pod Autoscaler)
echo "Aplicando o Horizontal Pod Autoscaler (HPA) para a API..."
kubectl apply -f hpa.yaml
if [ $? -ne 0 ]; then
    echo "Erro ao aplicar o HPA. Abortando."
    exit 1
fi
echo "HPA aplicado com sucesso."
echo ""

echo "Implantação concluída com sucesso!"
echo "Para verificar o status da aplicação, use os comandos:"
echo "kubectl get all -n desafio-fiap"
echo "kubectl describe hpa fastfood-app-hpa -n desafio-fiap"
echo "A aplicação FastFood API deve estar acessível via NodePort do seu cluster."