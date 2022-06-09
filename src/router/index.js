import Vue from "vue";
import Router from "vue-router";
import HomePage from "@/views/Home/index";
import DashboardPage from "@/views/Dashboard";
import { role } from "@/helpers/role";
import LoginPage from "@/views/Login";
import RegisterPage from "@/views/Register";
import CalendarPage from "@/views/Calendar";

Vue.use(Router);

export const router = new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      component: HomePage,
      meta: { authorize: [] },
    },
    {
      path: "/dashboard",
      component: DashboardPage,
      meta: { authorize: [role.admin] },
    },
    {
      path: "/login",
      component: LoginPage,
    },
    {
      path: "/register",
      component: RegisterPage,
    },
    {
      path: "/calendar",
      component: CalendarPage,
    },

    // otherwise redirect to home
    { path: "*", redirect: "/" },
  ],
});

router.beforeEach((to, from, next) => {
  // redirect to login page if not logged in and trying to access a restricted page
  const { authorize } = to.meta;
  const currentUser = JSON.parse(localStorage.getItem("currentUser"));

  if (authorize) {
    if (!currentUser) {
      // not logged in so redirect to login page with the return url
      return next({ path: "/login", query: { returnUrl: to.path } });
    }

    // check if route is restricted by role
    if (authorize.length && !authorize.includes(currentUser.role)) {
      // role not authorised so redirect to home page
      return next({ path: "/" });
    }
  }
  next();
});
