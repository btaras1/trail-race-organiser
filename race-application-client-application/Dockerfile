FROM node:18-alpine

WORKDIR /race-application-client-application/

COPY public/ /race-application-client-application/public
COPY src/ /race-application-client-application/src
COPY package.json /race-application-client-application/

RUN npm install

CMD ["npm", "start"]