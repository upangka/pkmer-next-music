<script setup lang="ts">
import {
  PkmerDropdownMenu,
  PkmerDropdownMenuContent,
  PkmerDropdownMenuTrigger,
  PkmerDropdownMenuItem
} from '@pkmer-music-ui/vue/dropdown'
</script>

<template>
  <section class="m-auto flex w-[50%] items-center justify-center bg-gray-300 p-4 shadow-md">
    <PkmerDropdownMenu>
      <PkmerDropdownMenuTrigger>
        <div
          class="flex h-[50px] w-[50px] items-center justify-center rounded-full bg-slate-500 text-white"
        >
          PK
        </div>
      </PkmerDropdownMenuTrigger>
      <PkmerDropdownMenuContent>
        <PkmerDropdownMenuItem><a href="#">I'm a dropdown.</a></PkmerDropdownMenuItem>
        <PkmerDropdownMenuItem><a href="#">In Pure CSS</a></PkmerDropdownMenuItem>
        <PkmerDropdownMenuItem><a href="#">As in...</a></PkmerDropdownMenuItem>
        <PkmerDropdownMenuItem><a href="#">No JavaScript.</a></PkmerDropdownMenuItem>
        <PkmerDropdownMenuItem><a href="#">At All.</a></PkmerDropdownMenuItem>
      </PkmerDropdownMenuContent>
    </PkmerDropdownMenu>
  </section>
</template>
