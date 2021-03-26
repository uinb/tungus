# Tungus

[![License](https://img.shields.io/badge/License-Apache%202.0-orange.svg)](#LICENSE) [![PRs Welcome](https://img.shields.io/badge/PRs-welcome-blue.svg)](CONTRIBUTING.md)

Tungus is a blockchain explorer of FUSOTAO, which provides a view of blocks, extrinsics, events and account-infos from a browser.

## Before

Tungus depends on [polkaj](https://github.com/emeraldpay/polkaj), please install polkaj first.

```bash

# clone the polkaj project

git clone https://github.com/emeraldpay/polkaj

# enter the project directory

cd polkaj

# install polkaj

./gradlew install -Dmaven.repo.local=~/.m2/repository/
```

## Development

```bash

# clone the project

git clone https://github.com/uinb/tungus.git

# enter the project directory

cd tungus

# build the project

mvn package

# start a local server

nohup java -jar /target/tungus-0.0.1-SNAPSHOT.jar > tungus.log &

```

The project will be running at [http://localhost:8080](http://localhost:8080)

## For frontend developers

The following steps maybe helpful to a frontend developer.

```bash
# enter the frontend project directory

cd src/main/resources/webapp

# install dependencies

npm install / yarn

# start a delevopment server at http://localhost:8080

npm run / yarn start

# build the project

npm run / yarn build
```

## Contributing

Please follow the [CONTRIBUTING GUIDE](CONTRIBUTING.md)

## License

This project is licensed under [APACHE2-LICENSE](LICENSE).
