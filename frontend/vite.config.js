import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
    server: {
      proxy: {
      // Target is your backend API
        '/api': {
            target: 'http://localhost:8080/api/', // /?filter=globaltopsellers&ignore_preferences=1&json=1
            changeOrigin: true,
            rewrite: (path) => path.replace(/^\/api/, ''),
            
            configure: (proxy, options) => {
               proxy.on('error', (err, _req, _res) => {
                console.log('error', err);
               });
               proxy.on('proxyReq', (proxyReq, req, _res) => {
                console.log('Request sent to target:', req.method, req.url);
                console.log("test" + req.headers);
               });
               proxy.on('proxyRes', (proxyRes, req, _res) => {
                console.log('Response received from target:', proxyRes.statusCode, req.url);
               });
         },
      },
    },
  },
 })
