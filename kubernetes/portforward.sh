#!/bin/bash
target_port=$(kubectl get svc fastfood-app -n desafio-fiap -o jsonpath='{.spec.ports[0].port}')
pod=$(kubectl get pods -n desafio-fiap -l app=fastfood-app -o jsonpath='{.items[0].metadata.name}')

echo "Port forwarding localhost:8080 -> $pod:$target_port"
echo "Port forwarding established. Access the application at http://localhost:8080/swagger-ui.html"
kubectl port-forward -n desafio-fiap $pod 8080:$target_port
