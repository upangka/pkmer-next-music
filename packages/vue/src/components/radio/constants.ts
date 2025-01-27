import type { ModelRef, InjectionKey } from 'vue'
interface RadioContext {
  modelValue: ModelRef<string>
  onChange: (value: string) => void
}

export const radioKey: InjectionKey<RadioContext> = Symbol('radio')
