<script setup>
import { LogOut, Pencil, LeafIcon, NotebookPen } from 'lucide-vue-next';
import { useUserStore } from "../stores/userStore";
import { useRouter } from 'vue-router';
import logo from '@/assets/img/default.jpg';
import ButtonMenu from './ButtonMenu.vue';

const userStore = useUserStore();
const router = useRouter();

const { user } = userStore;

const items = [
    {icon: Pencil, text: "Éditer profil", clickFunction: () => router.push('/profile/edit') },
    {icon: LogOut, text: "Déconnexion", clickFunction: () => userStore.logout() },
]
</script>

<template>
    <div class="flex items-center justify-between my-5">
        <div class="flex items-center">
            <img class="w-16 h-16 rounded-full border border-gray-400 object-cover" :src="user.photoURL || logo" alt="profile picture">
            <p class="ml-2 text-2xl">{{ user.displayName }}</p>
        </div>

        <ButtonMenu :items="items"></ButtonMenu>
    </div>

    <div class="flex items-center gap-2 mb-5">
        <RouterLink
        to="/profile/plants"
        class="w-full flex items-center justify-center gap-4 bg-green-600 text-white rounded-md drop-shadow-md py-1 hover:bg-green-700 transition-all ease-in-out duration-200"
      >
        <LeafIcon></LeafIcon>
        <p>Vos plantes</p>
      </RouterLink>

      <RouterLink
        to="/profile/notes"
        class="w-full flex items-center justify-center gap-4 bg-green-600 text-white rounded-md drop-shadow-md py-1 hover:bg-green-700 transition-all ease-in-out duration-200"
      >
        <NotebookPen></NotebookPen>
        <p>Vos notes</p>
      </RouterLink>
    </div>

    <hr class="bg-gray-400 h-[1px] border-none">
</template>