<script setup>
import { ref, watch } from "vue";
import { useUserStore } from "../stores/userStore";
import { isEmailValid } from "../assets/js/utils";
import GoogleSignIn from "../components/GoogleSignIn.vue";
import Input from "../components/Input.vue";
import Button from "../components/Button.vue";
import Title from "../components/Title.vue";

const userStore = useUserStore();

const email = ref("");
const emailError = ref("");
const password = ref("");
const rememberMe = ref(false);

watch(email, (value) => {
  if (!isEmailValid(value)) {
    emailError.value = "L'email n'est pas valide";
  } else {
    emailError.value = "";
  }
});

// TODO: add remember me feature
// TODO: add forgot password feature
// TODO: add error handling
const handleSubmit = async () => {
  try {
    await userStore.login(email.value, password.value);
  } catch (error) {
    console.log(error);
  }
};
</script>

<template>
  <div
    class="flex flex-col items-center justify-center h-screen md:w-1/2 mx-auto lg:w-1/3"
  >
    <Title class="mb-5">Connectez-vous à votre compte</Title>

    <form @submit.prevent="handleSubmit" class="flex flex-col gap-4 w-5/6">
      <Input
        name="Email"
        v-model="email"
        placeholder="Ex: johndoe@mail.com"
        required
        :error-message="emailError"
      />

      <Input
        name="Mot de passe"
        v-model="password"
        type="password"
        placeholder="********"
        required
      />

      <div class="flex items-center gap-2">
        <input
          type="checkbox"
          id="remember"
          v-model="rememberMe"
          class="w-4 h-4 text-green-600 accent-green-600 bg-gray-100 rounded-md cursor-pointer"
        />
        <label
          for="remember"
          class="text-sm font-medium text-gray-500 cursor-pointer"
          >Se souvenir de moi</label
        >
      </div>

      <Button type="submit" class="text-white bg-green-600 hover:bg-green-700"
        >Se connecter</Button
      >
    </form>

    <GoogleSignIn />
  </div>
</template>
