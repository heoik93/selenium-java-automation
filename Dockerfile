FROM maven:3.8.5-openjdk-17-slim

RUN apt-get update && apt-get install -y \
    locales \
    wget gnupg2 unzip fonts-nanum \
    && locale-gen ko_KR.UTF-8 \
    && wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list \
    && apt-get update && apt-get install -y google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

# 시스템 및 자바 전체에 UTF-8 적용
ENV LANG=ko_KR.UTF-8
ENV LANGUAGE=ko_KR.UTF-8
ENV LC_ALL=ko_KR.UTF-8
ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF-8"

WORKDIR /app
COPY . .

RUN mvn dependency:go-offline

# 빌드 시 인코딩 옵션 명시
ENTRYPOINT ["mvn", "test", "-Dfile.encoding=UTF-8"]