From node:carbon

WORKDIR /app

COPY . .

RUN npm install 

RUN npm install http-server -g 

RUN npm run build --prod

WORKDIR /app/dist/landing

CMD ["http-server", "-c1", "-p", "8063"]
