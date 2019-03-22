FROM node:9.6.1 as node
WORKDIR /usr/src
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
EXPOSE 4300
# Stage 2
FROM node
RUN npm install http-server -g
CMD ["http-server","-p","4300", "dist/SettlementDashboardForInsurer"]
