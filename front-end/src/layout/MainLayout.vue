<script setup>
import { RouterView } from "vue-router";
import Navbar from "@/components/Navbar.vue";
import Footer from "../components/Footer.vue";
import MobileNavbar from "../components/MobileNavbar.vue";
import { onMounted, onUnmounted, ref } from "vue";
import {
  NewspaperIcon,
  CalendarIcon,
  UsersIcon,
  UserIcon,
  HelpCircleIcon,
} from "lucide-vue-next";

const menuItems = [
  { name: "Fil d'actualité", path: "/posts", icon: NewspaperIcon },
  { name: "Calendrier", path: "/calendar", icon: CalendarIcon },
  { name: "Communauté", path: "/community", icon: UsersIcon },
  { name: "Mon profil", path: "/profile", icon: UserIcon },
  { name: "Aide", path: "/help", icon: HelpCircleIcon },
];

const isMobileScreen = ref(false);

const checkIfMobileScreen = () => {
  if (window.innerWidth <= 640) {
    isMobileScreen.value = true;
    return;
  }

  isMobileScreen.value = false;
};

onMounted(() => {
  checkIfMobileScreen();
  window.addEventListener("resize", checkIfMobileScreen);
});

onUnmounted(() => {
  window.removeEventListener("resize", checkIfMobileScreen);
});
</script>

<template>
  <div class="min-h-screen flex flex-col md:grid md:grid-cols-[auto_1fr] gap-4">
    <MobileNavbar :menuItems="menuItems" v-if="isMobileScreen" />
    <Navbar :menuItems="menuItems" v-else />

    <div class="w-full flex flex-col mt-5">
      <main class="mb-5">
        <RouterView v-slot="{ Component }">
          <transition name="fade" mode="out-in">
            <component :is="Component"></component>
          </transition>
        </RouterView>
      </main>

      <Footer v-if="!isMobileScreen"></Footer>
    </div>
  </div>
</template>
