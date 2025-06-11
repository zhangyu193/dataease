# Integrating DataEase Frontend with RuoYi-Vue3-ts

This guide explains how to merge the DataEase frontend located in `core/core-frontend` into the RuoYi-Vue3-ts framework and use RuoYi's menu-based permission system. The DataEase backend service continues to run separately.

## 1. Overview

DataEase's frontend is a Vite based Vue3 project. Important pages include the **workbench**, **dashboard**, and **dataset** modules defined in `src/router/index.ts`:

```ts
export const routes: AppRouteRecordRaw[] = [
  {
    path: '/',
    name: 'index',
    redirect: '/workbranch/index',
    component: () => import('@/layout/index.vue'),
    hidden: true,
    meta: {},
    children: [
      {
        path: 'workbranch',
        name: 'workbranch',
        hidden: true,
        component: () => import('@/views/workbranch/index.vue'),
        meta: { hidden: true }
      }
    ]
  },
  {
    path: '/dashboard',
    name: 'dashboard',
    hidden: true,
    meta: {},
    component: () => import('@/views/dashboard/index.vue')
  },
  {
    path: '/dataset-embedded',
    name: 'dataset-embedded',
    hidden: true,
    meta: {},
    component: () => import('@/views/visualized/data/dataset/index.vue')
  },
  {
    path: '/dataset-embedded-form',
    name: 'dataset-embedded-form',
    hidden: true,
    meta: {},
    component: () => import('@/views/visualized/data/dataset/form/index.vue')
  },
  // ...
]
```

RuoYi-Vue3-ts is also based on Vue3 with Vite and provides menu management and permission control out of the box.

## 2. Prepare the Projects

1. Obtain both repositories:
   - `zhangyu193/RuoYi-Vue3-ts`
   - `zhangyu193/database` (this repository) – frontend path `core/core-frontend`.
2. Install dependencies for each frontend project using `npm install`.
3. Ensure the DataEase backend service is running (default port 8100 as shown in `config/dev.ts`).

## 3. Move DataEase Frontend into RuoYi

1. Create a new directory in the RuoYi frontend project, for example `src/modules/dataease`.
2. Copy the contents of `core/core-frontend/src` from this repository into that folder.
3. Copy configuration files such as `vite.config.ts`, `postcss.config.js`, and `tsconfig.json` as needed. Merge them with RuoYi's existing configuration so that only one build is produced.
4. Remove the original `package.json` scripts from DataEase and rely on the RuoYi build commands.

## 4. Integrate the Router

1. Examine the DataEase routes under `src/router/index.ts`. Key routes include:
   - Workbench: `'/workbranch/index'`
   - Dashboard: `'/dashboard'`
   - Dataset: `'/dataset-embedded'` and `'/dataset-embedded-form'`
   These routes are defined around lines 8‑114 of the file `core/core-frontend/src/router/index.ts`.
2. In the RuoYi project, import these routes as a module. You can either register them directly inside RuoYi's router or lazy‑load them with `defineAsyncComponent`.
3. Add menu entries in RuoYi's **menu management** pointing to these paths. Give each menu the required permission code according to RuoYi's rules.

## 5. Disable DataEase Permission Checks

DataEase provides its own permission guard in `src/permission.ts`. This file contains route white lists and token checks:

```
const whiteList = ['/login', '/de-link', '/chart-view', '/admin-login', '/401']
...
router.beforeEach(async (to, from, next) => {
  ...
  if (wsCache.get('user.token') ...) {
    ...
  } else {
    next(`/login?redirect=${to.fullPath || to.path}`)
  }
})
```
(see lines 24‑86 and 120‑235).

Since RuoYi will handle authentication, remove this guard and rely solely on RuoYi's `permission.ts`.

## 6. Configure API Prefixes

In development, DataEase proxies API calls to the backend at port 8100 as configured in `config/dev.ts`:

```
server: {
  proxy: {
    '/api/f': {
      target: 'http://localhost:8100',
      changeOrigin: true,
      rewrite: path => path.replace(/^\/api\/f/, '')
    },
    '/api': {
      target: 'http://localhost:8100',
      changeOrigin: true,
      rewrite: path => path.replace(/^\/api/, 'de2api')
    }
  },
  port: 8080
}
```
(see lines 1‑18 of `config/dev.ts`). Add equivalent proxy settings in RuoYi's `vite.config.ts`.

## 7. Menu Setup in RuoYi

1. Open RuoYi's menu management page.
2. Create menu **A** with path `/workbranch/index`.
3. Create menu **B** with path `/dashboard`.
4. Create menu **C** with path `/dataset-embedded`.
5. Assign appropriate permission identifiers to each menu. Users will access DataEase pages only if they have these permissions.

## 8. Unified Build and Run

1. Ensure all DataEase source files are placed under `src/modules/dataease` in RuoYi.
2. Update RuoYi's `tsconfig.json` and path aliases to include `@/modules/dataease`.
3. Use the existing RuoYi `npm run dev` and `npm run build` commands. Vite will process both the original RuoYi code and the DataEase module.
4. Start the DataEase backend service separately.
5. Run the RuoYi frontend; navigating via the new menus should load the DataEase pages.

## 9. Notes

- Avoid using iframe embedding. All DataEase components are compiled together with the RuoYi app.
- If DataEase uses global styles or assets, merge them into RuoYi's global assets folder.
- For updates from upstream DataEase, repeat the copy/merge process or consider using `git subtree`.

