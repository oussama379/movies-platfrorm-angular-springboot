#!/usr/bin/env node

      import {createRequire as __cjsCompatRequire} from 'module';
      const require = __cjsCompatRequire(import.meta.url);
      const __ESM_IMPORT_META_URL__ = import.meta.url;
    
import {
  main
} from "../../chunk-TTIGAMYZ.js";
import "../../chunk-NDIOQ2EH.js";
import "../../chunk-J6AVV3MN.js";
import "../../chunk-NJ2FQOJT.js";
import "../../chunk-XR6BVLNN.js";
import "../../chunk-CLRZAXXE.js";
import {
  NodeJSFileSystem,
  setFileSystem
} from "../../chunk-4NRCP3Y6.js";
import "../../chunk-RCXOJZDO.js";
import {
  __require,
  __toESM
} from "../../chunk-XYNRD7NE.js";

// bazel-out/darwin_arm64-fastbuild/bin/packages/compiler-cli/src/bin/ngc.mjs
import "reflect-metadata";
async function runNgcComamnd() {
  process.title = "Angular Compiler (ngc)";
  const args = process.argv.slice(2);
  setFileSystem(new NodeJSFileSystem());
  let tsickleModule;
  try {
    tsickleModule = (await Promise.resolve().then(() => __toESM(__require("tsickle"), 1))).default;
  } catch {
  }
  process.exitCode = main(args, void 0, void 0, void 0, void 0, void 0, tsickleModule);
}
runNgcComamnd().catch((e) => {
  console.error(e);
  process.exitCode = 1;
});
/**
 * @license
 * Copyright Google LLC All Rights Reserved.
 *
 * Use of this source code is governed by an MIT-style license that can be
 * found in the LICENSE file at https://angular.io/license
 */
//# sourceMappingURL=ngc.js.map
