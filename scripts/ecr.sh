aws sso login --profile isuru@swingiot
aws ecr get-login-password --region eu-south-1  --profile isuru@swingiot | docker login --username AWS --password-stdin 351647157820.dkr.ecr.eu-south-1.amazonaws.com
docker build --platform linux/arm64/v8 -t swingiot/onboarder:latest .
docker tag swingiot/onboarder:latest 351647157820.dkr.ecr.eu-south-1.amazonaws.com/swingiot/onboarder:latest
docker push 351647157820.dkr.ecr.eu-south-1.amazonaws.com/swingiot/onboarder:latest