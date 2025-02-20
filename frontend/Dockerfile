FROM node:hydrogen-alpine3.21
WORKDIR /frontend
COPY . .
RUN yarn install --production
RUN npm install
# RUN npm run dev
EXPOSE 5173

CMD ["npm", "run", "dev"]