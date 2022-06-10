import Vue from "vue";
import VueRouter from "vue-router";

// router : 메뉴달기 비교) springboot : controller (메뉴달기)
Vue.use(VueRouter);

const routes = [
  // Test2
  {
    path: "/test2",
    name: "test2",
    component: () => import("@/views/Test2View"),
  },
  // AddCustomer
  {
    path: "/test",
    name: "test",
    component: () => import("@/views/TestView"),
  },
  // AddCustomer
  {
    path: "/add-customer",
    name: "add-customer",
    component: () => import("@/components/customer/AddCustomer"),
  },
  // CustomerList
  {
    path: "/customers",
    name: "customers",
    component: () => import("@/components/customer/CustomerList"),
  },
  // EditCustomer
  {
    path: "/customers/:id",
    name: "edit-customer",
    component: () => import("@/components/customer/EditCustomer"),
  },
  {
    path: "/",
    alias: "/home",
    name: "home",
    component: () => import("@/views/TestView"),
  },
  // /complain
  {
    path: "/complain",
    name: "complain",
    component: () => import("@/views/ComplainView"),
  },
  // /upload
  {
    path: "/upload",
    name: "upload",
    component: () => import("@/components/UploadImage"),
  },
  // /, /tutorials 메뉴를 클릭하면 TutorialsList.vue 로딩
  // TutorialsList.vue : Tutorial 목록 화면
  {
    path: "/tutorials",
    name: "tutorials",
    component: () => import("@/components/tutorials/TutorialsList"),
  },
  // Tutorial.vue : Tutorial 상세 화면
  {
    // :id => params
    path: "/tutorials/:id",
    name: "tutorial-details",
    component: () => import("@/components/tutorials/Tutorial"),
  },
  // AddTutorial.vue : Tutorial 에 목록 추가 화면
  {
    path: "/add",
    name: "add",
    component: () => import("@/components/tutorials/AddTutorial"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
