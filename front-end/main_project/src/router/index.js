import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  // {
  //   path: '/',
  //   component: Layout,
  //   redirect: '/dashboard',
  //   children: [{
  //     path: 'dashboard',
  //     name: 'Dashboard',
  //     component: () => import('@/views/dashboard/index'),
  //     title: '大数据管理',
  //     icon: 'el-icon-s-data'
  //   },
  //   {
  //     path: 'user',
  //     name: 'User',
  //     icon: 'el-icon-user-solid',
  //     title: '用户管理',
  //     component: ()=>import('@/views/user/index'),
  //   }]
  // },

  // {
  //   path: '/picture',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'index',
  //       name: 'Picture',
  //       component: () => import('@/views/picture/index'),
  //       title: '图片管理',
  //       icon: 'el-icon-picture'

  //     }
  //   ]
  // },
  // {
  //   path: '/repository',
  //   component: Layout,
  //   redirect: '/repository/disease-prevention',
  //   name: 'Repository',
  //   title: '知识库',
  //   icon: 'el-icon-info',
  //   children: [
  //     {
  //       path: 'disease-prevention',
  //       component: () => import('@/views/repository/disease-prevention/index.vue'),
  //       title: '植物病害防害知识库',
  //       icon: 'el-icon-s-management'
  //     },
  //     {
  //       path: 'agricultural-knowledge',
  //       component: () => import('@/views/repository/agricultural-knowledge/index.vue'),
  //       title: '农业知识技术库',
  //       icon: 'el-icon-s-management'
  //     }
  //   ]
  // },
  // {
  //   path: '/publish',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'index',
  //       component: () => import('@/views/publish/index.vue'),
  //       icon: 'el-icon-s-promotion',
  //       title: '助农信息发布'
  //       // meta: { ,  }
  //     }
  //   ]
  // },

  // 404 page must be placed at the end !!!
  // { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export const setRoutes = () => {
  const storeMenus = localStorage.getItem('menus')
  if (storeMenus) {
    // 获取当前的路由对象名称数组
    const currentRouteNames = router.getRoutes().map(v => v.name)
    if (!currentRouteNames.includes('user')) {
      // 拼装动态路由
      const manageRoute = {
        path: '/',
        name: 'Layout',
        component: (resolve) => require([`@/layout/index`], resolve),
        redirect: '/dashboard',
        children: [
          {
            path: 'person',
            component: () => import('@/views/person/index')
          }
        ]
      }
      const menus = JSON.parse(storeMenus)
      menus.forEach(item => {
        if (item.path) {
          const itemMenu = {
            path: item.path.replace('/', ''),
            name: item.name,
            component: (resolve) => require([`@/views/${item.pagePath}.vue`], resolve)
          }
          manageRoute.children.push(itemMenu)
        } else if (item.children.length) {
          item.children.forEach(i => {
            if (i.path) {
              const itemMenu = {
                path: i.path.replace('/', ''),
                name: i.name,
                component: (resolve) => require([`@/views/${i.pagePath}.vue`], resolve)
              }
              manageRoute.children.push(itemMenu)
            }
          })
        }
      })
      // 动态添加到现在的路由对象中去
      router.addRoute(manageRoute)
    }
  }
}

// 页面刷新再set一次路由
setRoutes()

export default router
