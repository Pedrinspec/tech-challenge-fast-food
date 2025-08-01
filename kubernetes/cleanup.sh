#!/bin/bash

echo "Iniciando a remoção dos recursos da aplicação FastFood no Kubernetes..."

echo "Deletando Horizontal Pod Autoscaler 'fastfood-app-hpa'..."
kubectl delete -f hpa.yaml --ignore-not-found

echo "Deletando Service 'fastfood-app'..."
kubectl delete -f service-api.yaml --ignore-not-found

echo "Deletando Deployment 'fastfood-app'..."
kubectl delete -f deployment-api.yaml --ignore-not-found

echo "Deletando StatefulSet 'mysql'..."
kubectl delete -f mysql-statefulset.yaml --ignore-not-found

echo "Deletando Service 'mysql'..."
kubectl delete -f mysql-service.yaml --ignore-not-found

echo "Deletando ConfigMaps 'mysql-config' e 'fastfood-app-config'..."
kubectl delete -f mysql-configmap.yaml --ignore-not-found
kubectl delete -f configmap-api.yaml --ignore-not-found

echo "Deletando Secrets 'mysql-secret' e 'fastfood-app-secret'..."
kubectl delete -f mysql-secret.yaml --ignore-not-found
kubectl delete -f secret-api.yaml --ignore-not-found

echo "Deletando Namespace 'desafio-fiap'..."
kubectl delete -f namespace.yaml --ignore-not-found

echo "Remoção concluída. Pode levar alguns segundos para que todos os recursos sejam encerrados."